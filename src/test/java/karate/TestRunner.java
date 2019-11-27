package karate;

import com.intuit.karate.junit5.Karate;

class TestRunner {

    @Karate.Test
    Karate testAll(){
        return new Karate().relativeTo(getClass());
    }


    @Karate.Test
    Karate testBooks(){
        return new Karate().feature("BookTest").relativeTo(getClass());
    }
}
