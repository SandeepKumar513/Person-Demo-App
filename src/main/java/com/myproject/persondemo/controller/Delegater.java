package com.myproject.persondemo.controller;

import com.myproject.persondemo.service.OptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class Delegater {
    @Autowired
    OptionService optService;
    Scanner scanner=new Scanner(System.in);
    String ch;
    private static Logger logger = LoggerFactory.getLogger(Delegater.class);

    public void options() {
        System.out.println("***********************************");
        System.out.println("Please select from below options");
        System.out.println("1) Add Person.");
        System.out.println("2) Edit Person.");
        System.out.println("3) Delete Person");
        System.out.println("4) Count Number of persons");
        System.out.println("5) List of All Persons");
        System.out.println("***********************************");
        System.out.print("Please enter your option:");

        try {
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    optService.add();
                    break;
                case 2:
                    optService.edit();
                    break;
                case 3:
                    optService.delete();
                    break;
                case 4:
                    optService.count();
                    break;
                case 5:
                    optService.getAll();
                    break;
                default:
                    System.out.println("Invalid Option...Please Try Again.");
                    break;
            }
        }
        catch(Exception e){
            System.out.println("Invalid Option!...Please Try Again.");
            logger.debug("Invalid entry",e);
        }
        System.out.print("Do you want to continue:(Y/N):");
        ch=scanner.next();
        if(ch.equalsIgnoreCase("Y"))
            options();
        else if(ch.equalsIgnoreCase("N"))
            System.exit(0);
        else
            System.out.println("=======================================");
            System.out.println("Invalid entry...Program Terminated...!");
            System.out.println("=======================================");
        System.exit(0);
    }
}
