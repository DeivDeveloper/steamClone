package org.steamclone.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.steamclone.dto.BusinessDTO;
import org.steamclone.model.entities.Business;
import org.steamclone.repositories.BusinessRepo;
import org.steamclone.services.interfaces.BusinessInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessInterfaceImpl implements BusinessInterface {

    @Autowired
    private BusinessRepo businessRepo;

    @Override
    public int createBusiness(BusinessDTO businessDTO) throws Exception {

        Business flagName = businessRepo.getBusinessByName(businessDTO.getName());

        if (flagName != null){
            throw new Exception("El nombre " + businessDTO.getName() + " ya existe");
        }

        Business business = new Business();

        business.setBusinessType(businessDTO.getBusinessType());
        business.setName(businessDTO.getName());
        business.setState(true);

        businessRepo.save(business);

        return business.getId();
    }

    @Override
    public int updateBusiness(int idBusiness, BusinessDTO businessDTO) throws Exception {

        Optional<Business> foundBusiness = businessRepo.findById(idBusiness);

        if (foundBusiness.isEmpty()) {
            throw new Exception("La empresa con id " + idBusiness + " no existe");
        }

        Business updateBusiness = foundBusiness.get();

        updateBusiness.setName(businessDTO.getName());
        updateBusiness.setBusinessType(businessDTO.getBusinessType());

        businessRepo.save(updateBusiness);

        return updateBusiness.getId();
    }

    @Override
    public boolean deleteBusiness(int idBusiness) throws Exception {

        Optional<Business> foundBusiness = businessRepo.findById(idBusiness);

        if (foundBusiness.isEmpty()) {
            throw new Exception("La empresa con id " + idBusiness + " no existe");
        }

        Business deleteBusiness = foundBusiness.get();

        deleteBusiness.setState(false);

        businessRepo.save(deleteBusiness);

        return deleteBusiness.isState();
    }

    @Override
    public List<BusinessDTO> listByName(String name) {

        List<Business> listBusiness = businessRepo.findByName(name);
        List<BusinessDTO> listBusinessDTO = new ArrayList<>();

        for (Business business : listBusiness) {
            listBusinessDTO.add(convertToGetDTO(business));
        }

        return listBusinessDTO;
    }

    @Override
    public List<BusinessDTO> listByIdGame(int idGame) {

        List<Business> listBusiness = businessRepo.listBusinessByIdGame(idGame);
        List<BusinessDTO> listBusinessDTO = new ArrayList<>();

        for (Business business : listBusiness) {
            listBusinessDTO.add(convertToGetDTO(business));
        }

        return listBusinessDTO;
    }

    @Override
    public List<BusinessDTO> listByBusinessType(String businessType) {

        List<Business> listBusiness = businessRepo.findByBusinessType(businessType);
        List<BusinessDTO> listBusinessDTO = new ArrayList<>();

        for (Business business : listBusiness) {
            listBusinessDTO.add(convertToGetDTO(business));
        }

        return listBusinessDTO;
    }

    @Override
    public BusinessDTO getBusinessDTO(int idBusiness) {
        return convertToGetDTO(businessRepo.findById(idBusiness).get());
    }

    @Override
    public Business getBusiness(int idBusiness) {
        return businessRepo.findById(idBusiness).get();
    }

    private BusinessDTO convertToGetDTO(Business business) {
        BusinessDTO businessDTO = new BusinessDTO(
                business.getId(),
                business.getName(),
                business.isState(),
                business.getBusinessType()
        );

        return businessDTO;
    }
}
