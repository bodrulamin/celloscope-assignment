import 'dart:convert';
import 'package:crypto/crypto.dart';
import 'package:http/http.dart' as http;

import '../constant/apiconfig.dart';
import '../models/user.dart';

Map<String, String> requestHeaders = {
  'Content-type': 'application/json',
};

Future<http.Response> register(User user) async {

  user.password = md5.convert(utf8.encode(user.password)).toString();

  final response = await http.post(Uri.parse(userEndpoint),
      headers: requestHeaders, body: jsonEncode(user.toMap()));

    return response;

}

Future<http.Response> updateUser(User user) async {
  user.password = md5.convert(utf8.encode(user.password)).toString();

  final response = await http.put(Uri.parse(userEndpoint),
      headers: requestHeaders, body: jsonEncode(user.toMap()));

  return response;

}
Future<http.Response> login(User user) async {
  user.password = md5.convert(utf8.encode(user.password)).toString();

  final response = await http.post(Uri.parse(loginEndpoint),
      headers: requestHeaders, body: jsonEncode(user.toMap()));

    return response;

}
Future<http.Response> forgot(User user) async {
  user.password = md5.convert(utf8.encode(user.password)).toString();

  final response = await http.post(Uri.parse(forgotEndpoint),
      headers: requestHeaders, body: jsonEncode(user.toMap()));

  return response;

}