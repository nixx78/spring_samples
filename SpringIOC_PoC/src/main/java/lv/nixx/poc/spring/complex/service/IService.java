package lv.nixx.poc.spring.complex.service;

import lv.nixx.poc.spring.complex.Dto;
import lv.nixx.poc.spring.complex.Source;

public interface IService {
    Dto process(String request);
    Source getSource();
}
