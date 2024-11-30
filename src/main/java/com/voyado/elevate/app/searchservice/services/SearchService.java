package com.voyado.elevate.app.searchservice.services;

import com.voyado.elevate.app.searchservice.services.exception.SearchServiceException;

public interface SearchService {
    SearchResult search(String query) throws SearchServiceException;
}
