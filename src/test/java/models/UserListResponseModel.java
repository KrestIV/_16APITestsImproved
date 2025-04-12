package models;

import lombok.Data;

@Data
public class UserListResponseModel {
    int page, per_page, total, total_pages;
    UserListResponseDataModel[] data;
    UserListResponseSupportModel support;
}
