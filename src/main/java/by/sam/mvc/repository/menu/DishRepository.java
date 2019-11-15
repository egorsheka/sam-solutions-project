package by.sam.mvc.repository.menu;
import by.sam.mvc.models.menu.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class DishRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public void add(Dish dish1) {





//        Person person = new Person();
//        Phone phone1 = new Phone( "123-456-7890" );
//        Phone phone2 = new Phone( "321-654-0987" );
//
//        person.addPhone( phone1 );
//        person.addPhone( phone2 );
//        manager.persist( person );
//        manager.flush();

      //  person.removePhone( phone1 );


        Dish dish = new Dish();
        dish.setName("qq");
        dish.setDishType(DishType.APPETISER);
        dish.setCuisine(new Cuisine(126, "24tfr"));





        Cuisine cuisine = manager.find(Cuisine.class, 216);
        System.out.println(cuisine);

        cuisine.addDish(dish);
        dish.setCuisine(cuisine);
        manager.persist(cuisine);






    }

    @Transactional
    public Cuisine addCuisine(Cuisine cuisine) {
        if(cuisine.getId() == 0){
            manager.persist(cuisine);
        }
        return cuisine;
    }

}
