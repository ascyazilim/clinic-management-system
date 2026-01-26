package com.asc.clinicms.auth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestRoleController {

    @GetMapping("/admin/ping")
    public String admin() { return "ADMIN OK"; }

    @GetMapping("/doctor/ping")
    public String doctor() { return "DOCTOR OK"; }

    @GetMapping("/secretary/ping")
    public String secretary() { return "SEKRETER OK"; }
}
