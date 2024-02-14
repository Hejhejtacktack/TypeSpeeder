package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInterfaceService {

    private final IOService ioService;

    @Autowired
    public UserInterfaceService(IOService ioService) {
        this.ioService = ioService;
    }

    public String promptForInput(String promptMessage) {
        ioService.print(promptMessage);
        return this.ioService.readString();
    }

    public String promptForInput(List<?> list) {
        ioService.printList(list);
        return this.ioService.readString();
    }
}
