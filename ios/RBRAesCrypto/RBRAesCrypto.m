//
//  RBRAesCrypto.m
//  RBRAesCrypto
//

#import "RBRAesCrypto.h"
#import "SecurityUtil.h"
#import <CommonCrypto/CommonCryptor.h>

@implementation RBRAesCrypto

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(decryptAESFile:(NSString *)fSrc
                  fDst:(NSString *)fDst
                  appkey:(NSString *)key
                  gIv:(NSString *)gIv
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    [SecurityUtil decryptAESFile:fSrc fDst:fDst app_key:key gIv:gIv];
    resolve (fDst);
}
@end
