package com.profile_messages.profile_messages.service;

/**The IService interface defines a generic set of operations all 
 * interfaces should be able to and are going to need to perform. 
 * Implement it as much as possible.
 */
public interface IService<Key,Entity,Dto> {

    public void delete(Key sessionOrUsername);
    public void update(Key sessionOrUsername,Dto dataToUpdateWith);
    public Dto find(Key sessionOrUsername);
}
