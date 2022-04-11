import 'dart:convert';

import 'package:localstorage/localstorage.dart';

import '../models/user.dart';

final LocalStorage storage = LocalStorage('cis');

saveToStorage(User user) {
  storage.setItem('user', jsonEncode(user.toMap()));
}

clearStorage() async {
  await storage.clear();
}
