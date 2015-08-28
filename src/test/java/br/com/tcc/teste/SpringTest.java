package br.com.tcc.teste;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {
                "classpath:spring/datasource.xml",
                "classpath:spring/spring.xml" })
public class SpringTest extends Assert {

    @Inject
    protected ApplicationContext ctx;

    @Test
    public void testSpring() {
        assertNotNull(ctx);
    }

}
