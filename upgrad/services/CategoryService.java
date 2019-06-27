
package org.upgrad.services;

import org.upgrad.models.Category;

import java.util.List;

public interface CategoryService {

    void addCategory(String title, String description);

    String getTitle(int categoryid);


    List<Category> getAllCategories();
}