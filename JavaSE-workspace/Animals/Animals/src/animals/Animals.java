/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animals;

/**
 *
 * @author deannaji
 */
public class Animals {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          Dog d1= new Dog("jiffy");
          Cat c1= new Cat("lisa");
          d1.speak();
          c1.speak();
    }
    
}
