package com.conexia.saludcoop.common.test;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase de prueba base, gestionada por Spring, para efectuar pruebas unitarias
 * en la aplicación. Todo test unitario debe extender de esta clase abstracta.
 * 
 * @author Sebastián Matienzo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:conexia-context.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public abstract class SpringManagedBaseTest {

}
