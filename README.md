# Pixabay API example

This is a sample app project for the Pixabay API.

It allows you to search the Pixabay API for images and shows them in a list.

## Project setup
Before building the project, add an API key to the `local.properties` file in the root of the project.
If no such file exists, create it.

Add the API key with the name `pixabay_api_key` as follows:

```
pixabay_api_key="XXXXXXXX"
```

## Architecture
The app follows the MVVM pattern based on Jetpack libraries.
It uses view binding instead of data binding as I haven't worked with data binding, yet.

It works also in landscape mode but it doesn't feature any special layouts for landscape.

## Remaining todos

- Cache results with repository class and a local database (Room)
- Details page for search results
- Error handling for API (backend down, no internet, no results for query)
- Show list in grid layout for landscape orientation
- Tests