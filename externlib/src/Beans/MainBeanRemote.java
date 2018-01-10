/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author kevin
 */
@Remote
public interface MainBeanRemote {
        public String getVoornaamById(String id);
        public String getAchternaamById(String id);
        public List getGroepen();
        public List<Integer> getAantalGroepen();
        public void test();
        //public void hoi();
        public List getIds_van_studenten();
        public List getStudentenInGroep(int nr);
        //public List getVoorkeurStudent(String id);
        
        public void voegGroepToe();
        public int groepLeeg(int nr);
        public List getVoorkeurByGebruikerId(String id);        //Get alle records tabel Voorkeur op basis van de ingelogde gebruiker id
        public List getNietVoorkeurByGebruikerId(String id);
        public String getIdByFullName(String name);
        public void voegNvkToe(String gid, String nvk);
        public void voegVkToe(String gid, String vk);

        public void updateStudentBevestigt(String gid);
        public String isStudentBevestigt(String gid);
       
        public void deleteStudentUitGroep(int id);
        public void insertStudentInGroep(String id, int groepnummer);
        public void updateGroepsindeling(String id, int groepnummer);
        public void deleteNvkByGid(String gid);
        public void deleteNvkByGidAndNvk(String gid, String nvk);
        public void bevestigGroep(int groepnummer);
        public boolean isGroepBevestigt(int groepnummer);
}
