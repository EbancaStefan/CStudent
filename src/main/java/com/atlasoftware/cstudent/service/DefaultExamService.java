package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.ExamDao;
import com.atlasoftware.cstudent.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("examService")
public class DefaultExamService implements ExamService{
    @Autowired
    ExamRepository ExamRepository;

    @Override
    public List<ExamDao> getAllExam() {
        return ExamRepository.findAll();
    }

    @Override
    public ExamDao getExamByID(UUID id) {
        return ExamRepository.getById(id);
    }

    @Override
    public void deleteExamByID(UUID id) {
        ExamRepository.deleteById(id);
    }

    @Override
    public void deleteAllExamByID(List<UUID> ids)
    {
        ExamRepository.deleteAllById(ids);
    }

    @Override
    public ExamDao updateAndAddExam(ExamDao Exam) {
        return ExamRepository.saveAndFlush(Exam);
    }
    
}
