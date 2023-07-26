package org.steamclone.services.interfaces;

import org.steamclone.dto.BusinessDTO;
import org.steamclone.model.entities.Business;

import java.util.List;

public interface BusinessInterface {
    public int createBusiness(BusinessDTO businessDTO) throws Exception;
    public int updateBusiness(int idBusiness, BusinessDTO businessDTO) throws Exception;
    public boolean deleteBusiness(int idBusiness) throws Exception;
    public List<BusinessDTO> listByName(String name);
    public List<BusinessDTO> listByIdGame(int idGame);
    public List<BusinessDTO> listByBusinessType(String businessType);
    public BusinessDTO getBusinessDTO (int idBusiness);
    public Business getBusiness (int idBusiness);

}
