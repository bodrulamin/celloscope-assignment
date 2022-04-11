class ApiResponse {
  String message = "Empty message";
  Map<String, dynamic> data;
  int statusCode;

//<editor-fold desc="Data Methods">

  ApiResponse({
    required this.message,
    required this.data,
    required this.statusCode,
  });

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      (other is ApiResponse &&
          runtimeType == other.runtimeType &&
          message == other.message &&
          data == other.data &&
          statusCode == other.statusCode);

  @override
  int get hashCode => message.hashCode ^ data.hashCode ^ statusCode.hashCode;

  @override
  String toString() {
    return 'ApiResponse{' +
        ' message: $message,' +
        ' data: $data,' +
        ' statusCode: $statusCode,' +
        '}';
  }

  ApiResponse copyWith({
    String? message,
    Map<String, dynamic>? data,
    int? statusCode,
  }) {
    return ApiResponse(
      message: message ?? this.message,
      data: data ?? this.data,
      statusCode: statusCode ?? this.statusCode,
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'message': this.message,
      'data': this.data,
      'statusCode': this.statusCode,
    };
  }

  factory ApiResponse.fromMap(Map<String, dynamic> map) {
    return ApiResponse(
      message: map['message'] as String,
      data: map['data'] as Map<String, dynamic>,
      statusCode: map['statusCode'] as int,
    );
  }

//</editor-fold>
}
