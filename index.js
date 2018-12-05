import { NativeModules } from 'react-native';
const { RBRAesCrypto } = NativeModules;
export default {
	decryptAESFile(fSrc, fDst, secretKey, iv) {
        if (!fSrc || !fDst || !secretKey || iv === null || iv === undefined) {
            return Promise.reject(new Error("invalid parameter: fSrc, fDst, secretKey and iv are required"));
        }
		RBRAesCrypto.decryptAESFile(fSrc, fDst, secretKey, iv);
        return fDst;
    },
}
