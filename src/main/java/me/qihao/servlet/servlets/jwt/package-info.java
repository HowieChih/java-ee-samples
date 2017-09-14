/**
 * jwt test example.
 * all servlet is api-based and should access via API Test Tool.
 * <p>first: POST /jwt/login with userName(admin) and password(123456) to login and get jwt token</p>
 * <p>second: GET /jwt/detail?1 with HTTP Header <code>Authorization: Bearer &lt;token&gt;<code/></p>
 */
package me.qihao.servlet.servlets.jwt;