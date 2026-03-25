@api @test
Feature: Posts API
  As a consumer of the Posts API
  I want to be able to manage posts
  So that I can create, read, update and delete post resources

  Background:
    Given the Posts API is available

  @get @smoke
  Scenario: Get all posts returns a list
    When I request all posts
    Then the response status is 200
    And the response contains a list of posts

  @get @test
  Scenario: Get a single post by ID
    When I request post with id 1
    Then the response status is 200
    And the post has title "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"

  @create @test
  Scenario: Create a new post
    When I create a post with the following details:
      | title  | My New Post        |
      | body   | This is the body   |
      | userId | 1                  |
    Then the response status is 201
    And the response contains the created post id

  @notfound
  Scenario: Get a non-existing post returns 404
    When I request post with id 9999
    Then the response status is 404
