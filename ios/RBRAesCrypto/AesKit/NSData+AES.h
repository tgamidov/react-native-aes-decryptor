//  NSData+AES.h

#import <Foundation/Foundation.h>

@class NSString;

@interface NSData (Decryption)

- (NSData *)AES128DecryptWithKey:(NSString *)key gIv:(NSString *)gIv;

@end
