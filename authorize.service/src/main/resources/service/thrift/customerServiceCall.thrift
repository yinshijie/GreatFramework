namespace java com.enn.greatframework.authorize

struct CCustomer {

      1: string customerId

      2: string customerName

      3: string customerTitle

      4: string customerIconPath

      5: string customerPassword

      6: string customerMobile

      7: string customerEmail

      8: string customerCreatetime

      9: string customerParentId

      10: string customerSecMobile

      11: string customerSecEmail

      12: string customerState

      13: string customerSecQaEnable

      14: string customerSecControlEnable

      15: string customerSecCaEnable

      16: string customerEnable

      17: string updateTime
}

service  TCustomerService {
  CCustomer getCustomerBySessionId(1:string sessionId)
  
  CCustomer createCustomer(1:CCustomer cCustomer)
}