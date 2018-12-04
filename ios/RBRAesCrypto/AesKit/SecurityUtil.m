//  SecurityUtil.h

#import "SecurityUtil.h"
#import "NSData+AES.h"


@implementation SecurityUtil

#pragma mark - AES

+(void)decryptAESFile:(NSString*)fSrc fDst:(NSString*)fDst app_key:(NSString*)key gIv:(NSString *)gIv
{
    NSData *data = [[NSFileManager defaultManager] contentsAtPath:fSrc];
    NSData *decryData = [data AES128DecryptWithKey:key gIv:gIv];
    [decryData writeToFile:fDst atomically:YES];
}


@end
