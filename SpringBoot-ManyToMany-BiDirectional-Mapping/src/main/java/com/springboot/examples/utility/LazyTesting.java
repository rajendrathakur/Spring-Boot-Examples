package com.springboot.examples.utility;

import com.springboot.examples.entity.Manufacturer;
import com.springboot.examples.entity.Model;
import com.springboot.examples.repository.ManufacturerRepository;
import com.springboot.examples.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class LazyTesting {

    private ModelRepository modelRepository;
    private ManufacturerRepository manufacturerRepository;

   /* @Transactional
    public void insertManufacturerRecords() {
        Model suv = new Model("SUV", "5 Star");
        Model sedan = new Model("Sedan", "4 Star");
        Model hatchback = new Model("Hatchback", "3 Star");

        Manufacturer maruthi = new Manufacturer("Maruthi", "India");
        Manufacturer toyota = new Manufacturer("Toyota", "Germany");
        Manufacturer kia = new Manufacturer("KIA", "South Korea");

        //create Maruthi
        maruthi.getModels().add(suv);
        maruthi.getModels().add(sedan);

        //create Toyota
        toyota.getModels().add(sedan);
        toyota.getModels().add(hatchback);

        //create KIA
        kia.getModels().add(hatchback);

        manufacturerRepository.save(maruthi);
        manufacturerRepository.save(toyota);
        manufacturerRepository.save(kia);
    }*/

    @Transactional
    public void insertModelRecords() {
        Model suv = new Model("SUV", "5 Star");
        Model sedan = new Model("Sedan", "4 Star");
        Model hatchback = new Model("Hatchback", "3 Star");

        Manufacturer maruthi = new Manufacturer("Maruthi", "India");
        Manufacturer toyota = new Manufacturer("Toyota", "Germany");
        Manufacturer kia = new Manufacturer("KIA", "South Korea");

        //create SUV model
        suv.getManufacturers().add(maruthi);

        //create Sedan model
        sedan.getManufacturers().add(maruthi);
        sedan.getManufacturers().add(toyota);

        //create Hatchback model
        hatchback.getManufacturers().add(toyota);
        hatchback.getManufacturers().add(kia);

        modelRepository.save(suv);
        modelRepository.save(sedan);
        modelRepository.save(hatchback);

    }

    @Transactional
    public void insertManufacturerRecordsBidirectional() {
        Model suv = new Model("SUV", "5 Star");
        Model sedan = new Model("Sedan", "4 Star");
        Model hatchback = new Model("Hatchback", "3 Star");

        Manufacturer maruthi = new Manufacturer("Maruthi", "India");
        Manufacturer toyota = new Manufacturer("Toyota", "Germany");
        Manufacturer kia = new Manufacturer("KIA", "South Korea");

        //create Maruthi
        maruthi.getModels().add(suv);
        maruthi.getModels().add(sedan);

        //create Toyota
        toyota.getModels().add(sedan);
        toyota.getModels().add(hatchback);

        //create KIA
        kia.getModels().add(hatchback);

        //create SUV model
        suv.getManufacturers().add(maruthi);

        //create Sedan model
        sedan.getManufacturers().add(maruthi);
        sedan.getManufacturers().add(toyota);

        //create Hatchback model
        hatchback.getManufacturers().add(toyota);
        hatchback.getManufacturers().add(kia);

        modelRepository.save(suv);
        modelRepository.save(sedan);
        modelRepository.save(hatchback);

    }


    @Transactional
    public void deleteManufacturerRecordWithoutCascade() {
        Manufacturer toyota = manufacturerRepository.findById(2l).get();
        toyota.getModels().forEach(m -> m.getManufacturers().remove(toyota));
        modelRepository.saveAll(toyota.getModels());
        manufacturerRepository.deleteById(2l);
    }

    @Transactional
    public void deleteManufacturerRecordBetterWay() {
        Manufacturer maruthi = manufacturerRepository.findById(1l).get();
        maruthi.getModels().stream().forEach(m -> {
            if(m.getManufacturers().size() == 1) {
                modelRepository.deleteById(m.getId());
            } else {
                m.getManufacturers().remove(maruthi);
            }
        });
        manufacturerRepository.deleteById(maruthi.getId());
    }

    @Transactional
    public void deleteModelRecordBetterWay() {
        Model sedan = modelRepository.findById(2l).get();
        sedan.getManufacturers().stream().forEach(m -> {
            if (m.getModels().size() == 1) {
                manufacturerRepository.deleteById(m.getId());
            } else {
                m.getModels().remove(sedan);
            }
        });
        modelRepository.deleteById(sedan.getId());
    }
}
