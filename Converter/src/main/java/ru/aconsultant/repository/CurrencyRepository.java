package ru.aconsultant.repository;

import ru.aconsultant.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CurrencyRepository extends JpaRepository<Currency, String> {

    List<Currency> findAllByName(String name);//просто правильное название метода даст возможность
    //избежать запросов на SQL
    
    /*@Transactional
    public static void insertWithQuery(Currency c) {
    	
    	entityManager.createNativeQuery("INSERT INTO person (id, first_name, last_name) VALUES (?,?,?)")
    	.setParameter(1, 1)
    	.setParameter(2, 1)
    	.setParameter(3, 1)
    	.executeUpdate();
    }*/
    
    /*@Query("select u from Users u where u.email like '%@gmail.com%'")//если этого мало можно написать
    //собственный запрос на языке похожем на SQL
    List<Users> findWhereEmailIsGmail();
   
    @Query(value = "select * from users where name like '%smith%'", nativeQuery = true)
    //если и этого мало - можно написать запрос на чистом SQL и все это будет работать
    List<Users> findWhereNameStartsFromSmith();*/
}
