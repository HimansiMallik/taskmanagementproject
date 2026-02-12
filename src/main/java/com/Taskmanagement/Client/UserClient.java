package com.Taskmanagement.Client;

import java.util.Set;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Taskmanagement.Enum.Role;
@FeignClient(name = "user-service", url = "${user-service.url:http://localhost:8081}")
public interface UserClient {

    @GetMapping("/api/user/{email}/roles")
    Set<Role> getRole(@PathVariable("email") String userOfficialEmail);


}
