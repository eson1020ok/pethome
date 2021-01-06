package org.fgbokg.org;

import org.fgbokg.PetHomeApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 公共测试类，注解
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PetHomeApplication.class)
public class BaseTest {
}
