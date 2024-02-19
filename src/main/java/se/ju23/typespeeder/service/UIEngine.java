package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UIEngine implements UIService {

    private final IOService ioService;

    @Autowired
    public UIEngine(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public String promptForInput(String promptMessage) {
        ioService.print(promptMessage);
        return this.ioService.readString();
    }

    @Override
    public String promptForInput(List<?> list) {
        ioService.printList(list);
        return this.ioService.readString();
    }
}
