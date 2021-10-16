package com.unitec.amigos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepositorioUsuario extends MongoRepository <Usuario,String>
{

}
