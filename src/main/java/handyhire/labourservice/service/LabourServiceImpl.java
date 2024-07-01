package handyhire.labourservice.service;

import handyhire.labourservice.dto.LabourDTO;
import handyhire.labourservice.dto.SkillsDTO;
import handyhire.labourservice.enitiy.Labour;
import handyhire.labourservice.enitiy.Skills;
import handyhire.labourservice.exceptions.ExceptionMessages;
import handyhire.labourservice.repository.LabourRepository;
import handyhire.labourservice.repository.SkillsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LabourServiceImpl implements LabourService {

    private static final Logger logger = LoggerFactory.getLogger(LabourServiceImpl.class);

    @Autowired
    private LabourRepository labourRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    @Override
    public ResponseEntity<Labour> createLabour(LabourDTO labourDTO) throws Exception {
        logger.info("Creating labour with username: {}", labourDTO.getUsername());

        if (labourRepository.existsByUsername(labourDTO.getUsername())) {
            logger.error("Username {} already exists", labourDTO.getUsername());
            throw new Exception(ExceptionMessages.USERNAME_EXISTS);
        }
        if (labourRepository.existsByEmail(labourDTO.getEmail())) {
            logger.error("Email {} already exists", labourDTO.getEmail());
            throw new Exception(ExceptionMessages.EMAIL_EXISTS);
        }
        if (labourRepository.existsByPhoneNumber(labourDTO.getPhoneNumber())) {
            logger.error("Phone number {} already exists", labourDTO.getPhoneNumber());
            throw new Exception(ExceptionMessages.PHONE_NUMBER_EXISTS);
        }

        ModelMapper mapper = new ModelMapper();
        Labour labourToSave = mapper.map(labourDTO, Labour.class);

        List<Skills> skillsList = new ArrayList<>();
        for (SkillsDTO skillsDTO : labourDTO.getSkills()) {
            Skills skills = mapper.map(skillsDTO, Skills.class);
            skills.setLabour(labourToSave);
            skillsList.add(skills);
        }
        labourToSave.setSkills(skillsList);

        Labour savedLabour = labourRepository.save(labourToSave);
        logger.info("Created labour with ID: {}", savedLabour.getId());

        return ResponseEntity.ok(savedLabour);
    }

    @Override
    public List<Labour> getLabour(int pageNo, int pageSize) throws Exception {
        logger.info("Fetching labour list, page: {}, size: {}", pageNo, pageSize);

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Labour> labourPage = labourRepository.findAll(pageRequest);

        if (labourPage.isEmpty()) {
            logger.warn("No labour found");
            throw new Exception(ExceptionMessages.LABOUR_EMPTY);
        } else {
            logger.info("Fetched {} labour records", labourPage.getContent().size());
            return labourPage.getContent();
        }
    }

    @Override
    public ResponseEntity<Labour> getLabourById(int id) throws Exception {
        logger.info("Fetching labour with ID: {}", id);

        Labour labour = labourRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Labour with ID: {} not found", id);
                    return new Exception(ExceptionMessages.LABOUR_NOT_FOUND);
                });

        logger.info("Fetched labour with ID: {}", labour.getId());
        return ResponseEntity.ok(labour);
    }

    @Override
    public ResponseEntity<Labour> updateLabour(LabourDTO labourDTO) throws Exception {
        logger.info("Updating labour with ID: {}", labourDTO.getId());

        Labour existingLabour = labourRepository.findById(labourDTO.getId())
                .orElseThrow(() -> {
                    logger.error("Labour with ID: {} not found", labourDTO.getId());
                    return new Exception(ExceptionMessages.LABOUR_NOT_FOUND);
                });

        ModelMapper mapper = new ModelMapper();
        mapper.map(labourDTO, existingLabour);

        List<Skills> existingSkills = existingLabour.getSkills();
        Map<Long, Skills> existingSkillsMap = new HashMap<>();
        for (Skills skill : existingSkills) {
            existingSkillsMap.put((long) skill.getId(), skill);
        }

        List<Skills> updatedSkills = new ArrayList<>();
        for (SkillsDTO skillsDTO : labourDTO.getSkills()) {
            if (skillsDTO.getId() != 0) {
                Skills existingSkill = existingSkillsMap.get(skillsDTO.getId());
                if (existingSkill != null) {
                    existingSkill.setSkill(skillsDTO.getSkill());
                    updatedSkills.add(existingSkill);
                    existingSkillsMap.remove(skillsDTO.getId());
                }
            } else {
                Skills newSkill = mapper.map(skillsDTO, Skills.class);
                newSkill.setLabour(existingLabour);
                updatedSkills.add(newSkill);
            }
        }

        List<Skills> skillsToRemove = new ArrayList<>(existingSkillsMap.values());
        existingLabour.getSkills().removeAll(skillsToRemove);
        existingLabour.setSkills(updatedSkills);

        Labour savedLabour = labourRepository.save(existingLabour);
        logger.info("Updated labour with ID: {}", savedLabour.getId());

        return ResponseEntity.ok(savedLabour);
    }

    @Override
    public ResponseEntity<Labour> deleteLabourById(int id) throws Exception {
        logger.info("Deleting labour with ID: {}", id);

        Labour labour = labourRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Labour with ID: {} not found", id);
                    return new Exception(ExceptionMessages.LABOUR_NOT_FOUND);
                });

        labourRepository.delete(labour);
        logger.info("Deleted labour with ID: {}", id);

        return ResponseEntity.ok(labour);
    }
}
