class User {
  int userId;
  String mobile;
  String password;

//<editor-fold desc="Data Methods">

  User({
    required this.userId,
    required this.mobile,
    required this.password,
  });

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is User &&
          runtimeType == other.runtimeType &&
          userId == other.userId &&
          mobile == other.mobile &&
          password == other.password);

  @override
  int get hashCode => userId.hashCode ^ mobile.hashCode ^ password.hashCode;

  @override
  String toString() {
    return 'User{' +
        ' userId: $userId,' +
        ' mobile: $mobile,' +
        ' password: $password,' +
        '}';
  }

  User copyWith({
    int? userId,
    String? mobile,
    String? password,
  }) {
    return User(
      userId: userId ?? this.userId,
      mobile: mobile ?? this.mobile,
      password: password ?? this.password,
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'userId': this.userId,
      'mobile': this.mobile,
      'password': this.password,
    };
  }

  factory User.fromMap(Map<String, dynamic> map) {
    return User(
      userId: map['userId'] as int,
      mobile: map['mobile'] as String,
      password: map['password'] as String,
    );
  }

//</editor-fold>
}
