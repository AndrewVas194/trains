package com.example.trains.controllers;

import com.example.trains.models.Train;
import com.example.trains.repositories.TrainRepository;
import com.example.trains.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private TrainService trainService;

    @GetMapping("/list")
    public String trainMain (Model model){
        Iterable<Train> trains = trainRepository.findAll();
        model.addAttribute("trains",trains);
        model.addAttribute("title","Список поездов");
        return "train-list";
    }

    @GetMapping ("/add")
    public String trainAdd (Model model) {
        model.addAttribute("title","Добавление поезда");
        return "train-add";
    }
    /*@PreAuthorize("hasAuthority('ADMIN')")*/
    @PostMapping("/add")
    public String trainPostAdd(@RequestParam("trainsName") String trainsName,
                               @RequestParam ("sendfrom")String sendfrom,@RequestParam("tosend") String tosend,
                               @RequestParam("time_to_send") String time_to_send,@RequestParam("count_site_places") Integer count_site_places,
                               @RequestParam ("price")String price ,
                               Model model){
        trainService.saveTrainToDB(trainsName,sendfrom,tosend,time_to_send,count_site_places,price);
        return "redirect:/train/add";
    }



    @GetMapping("/buy/ticket/{id}")
    public String findById(@PathVariable(value = "id") Long id, Model model) {
        Optional<Train> trainId = trainRepository.findById(id);
        ArrayList<Train> res = new ArrayList<>();
        trainId.ifPresent(res::add);
        if(res.get(0).getCount_site_places()>0){
            res.get(0).setCount_site_places(res.get(0).getCount_site_places() - 1 );
        }
        else {return "ticked-sold";}
        trainRepository.save(res.get(0));
        model.addAttribute("title","Билет");
        model.addAttribute("trainId",res);
        return "buy-ticket";
    }

    @GetMapping("/toSend")
    public String filter(@RequestParam String filter, Model model){
        List<Train> trains = trainRepository.findByTosend(filter);
        model.addAttribute("trains", trains);
        model.addAttribute("title","Прибытие");
        return "train-list";
    }

    @GetMapping("/fromSend")
    public String filter1(@RequestParam String filter1, Model model){
        List<Train> trains = trainRepository.findBySendfrom(filter1);
        model.addAttribute("trains", trains);
        model.addAttribute("title","Отправление");
        return "train-list";
    }

}