package com.example.webmvc.dao;

import com.example.webmvc.entity.Statement;

import java.util.List;

public interface StatementDao {
    void saveRequest(Statement request);
    void updateRequest(Statement request);
    void deleteRequest(int id);
    Statement getRequestById(int id);
    List<Statement> getAllRequests();
}
