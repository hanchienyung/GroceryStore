package com.cy.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class GroceryStoreController {

    private double revenue_cosmetic = 0.0;
    private double revenue_cleaningitem = 0.0;
    private double revenue_snack = 0.0;
    private double revenue_total = 0.0;

    @Autowired
    GroceryStoreRepository grocerystoreRepository;

    @Autowired
    CosmeticRepository cosmeticRepository;

    @Autowired
    CleaningItemRepository cleaningitemRepository;

    @Autowired
    SnackRepository snackRepository;

    private BindingResult result;

    @RequestMapping("/")
    public String listGroceryStores(Model model) {
        model.addAttribute("grocerystores", grocerystoreRepository.findAll());
        model.addAttribute("cosmetics", cosmeticRepository.findAll());
        model.addAttribute("cleaningitems", cleaningitemRepository.findAll());
        model.addAttribute("snacks", snackRepository.findAll());

        //initialize sum variables
        revenue_cosmetic = 0.0;
        revenue_cleaningitem = 0.0;
        revenue_snack = 0.0;
        revenue_total = 0.0;

        for (Cosmetic cosmetic : cosmeticRepository.findAll())
        {
           revenue_cosmetic += cosmetic.getPrice() * cosmetic.getQty();
        }
        model.addAttribute("revenue_cosmetic", revenue_cosmetic);

        for (CleaningItem cleaningitem : cleaningitemRepository.findAll())
        {
            revenue_cleaningitem += cleaningitem.getPrice() * cleaningitem.getQty();
        }
        model.addAttribute("revenue_cleaningitem", revenue_cleaningitem);

        for (Snack snack : snackRepository.findAll())
        {
            revenue_snack += snack.getPrice() * snack.getQty();
        }
        model.addAttribute("revenue_snack", revenue_snack);

        revenue_total = revenue_cosmetic + revenue_cleaningitem + revenue_snack;

        model.addAttribute("revenue_total", revenue_total);
        return "list";
    }

    @GetMapping("/add")
    public String grocerystoreForm(Model model) {
        model.addAttribute("grocerystore", new GroceryStore());
        return "grocerystoreform";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute GroceryStore grocerystore, BindingResult result) {
        if (result.hasErrors()) {
            return "grocerystoreform";
        }
        grocerystoreRepository.save(grocerystore);
        return "redirect:/";
    }


    @GetMapping("/addcosm")
    public String cosmeticForm(Model model) {
        model.addAttribute("cosmetic", new Cosmetic());
        return "cosmeticform";
    }

    @PostMapping("/processcosm")
    public String processcosmForm(@Valid Cosmetic cosmetic, BindingResult result) {
        if (result.hasErrors()) {
            return "cosmeticform";
        }
        cosmeticRepository.save(cosmetic);
        return "redirect:/";
    }


    @GetMapping("/addclean")
    public String cleaningitemForm(Model model) {
        model.addAttribute("cleaningitem", new CleaningItem());
        return "cleaningitemform";
    }

    @PostMapping("/processclean")
    public String processcleanForm(@Valid CleaningItem cleaningitem, BindingResult result) {
        if (result.hasErrors()) {
            return "cleaningitemform";
        }
        cleaningitemRepository.save(cleaningitem);
        return "redirect:/";
    }

    @GetMapping("addsnack")
    public String snackForm(Model model) {
        model.addAttribute("snack", new Snack());
        return "snackform";
    }

    @PostMapping("/processsnack")
    public String processsnackForm(@Valid Snack snack, BindingResult result) {
        if (result.hasErrors()) {
            return "snackform";
        }
        snackRepository.save(snack);
        return "redirect:/";
    }



}

