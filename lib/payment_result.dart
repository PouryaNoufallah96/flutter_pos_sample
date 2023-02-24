class PaymentResult {
  final String status;
  final String terminalID;
  final String stan;
  final String rrn;
  final String responseCode;
  final String customerCardNO;
  final String transactionDateTime;
  final String description;
  const PaymentResult({
    required this.status,
    required this.terminalID,
    required this.stan,
    required this.rrn,
    required this.responseCode,
    required this.customerCardNO,
    required this.transactionDateTime,
    required this.description,
  });

  static PaymentResult fromjson(Map json) {
    return PaymentResult(
      status: json['status'],
      terminalID: json['terminalID'],
      stan: json['stan'],
      rrn: json['rrn'],
      responseCode: json['responseCode'],
      customerCardNO: json['customerCardNO'],
      transactionDateTime: json['transactionDateTime'],
      description: json['description'],
    );
  }
}
