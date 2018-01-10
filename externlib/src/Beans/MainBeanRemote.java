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
        public void hoi();
        public List getIds_van_studenten();
        public List getStudentenInGroep(int nr);
        public List getVoorkeurStudent(String id);
        //public List getIdByFullName(String name); //TODO
        public void voegGroepToe();
        public int groepLeeg(int nr);
}
