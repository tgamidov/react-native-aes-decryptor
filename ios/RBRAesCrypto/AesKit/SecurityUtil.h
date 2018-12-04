//  SecurityUtil.h

#import <Foundation/Foundation.h>

@interface SecurityUtil : NSObject 

#pragma mark - AES

+(void)decryptAESFile:(NSString*)fSrc fDst:(NSString*)fDst app_key:(NSString*)key gIv:(NSString *)gIv;

@end
