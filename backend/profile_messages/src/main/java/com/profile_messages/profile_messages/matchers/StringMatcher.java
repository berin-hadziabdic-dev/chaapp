package com.profile_messages.profile_messages.matchers;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class StringMatcher {

    public boolean match(String one,String two)
    {
        boolean retval = false;

        if(one != null && two != null)
            retval = one.equals(two);
        
        return retval;
    }
    
}
