package lv.nixx.poc.spring.complex.service;

import lv.nixx.poc.spring.complex.Dto;
import lv.nixx.poc.spring.complex.Source;
import org.springframework.stereotype.Service;

@Service
public class AlphaService implements IService{

    @Override
    public Dto process(String request) {
        return new Dto("AlphaF1." + request, "AlphaF2." + request);
    }

    @Override
    public Source getSource() {
        return Source.Alpha;
    }
}
