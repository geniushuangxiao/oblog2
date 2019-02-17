import { FormControl } from '@angular/forms';
export class MyValidators {
    public static email(c: FormControl) {
        const EMAIL_REGEXP = new RegExp("[a-z0-9]+@[a-z0-9]+.com");
        return EMAIL_REGEXP.test(c.value)? null : {
            email: '邮箱格式不合法'
        }
    }
    public static required(c: FormControl) {
        return c.value? null : {
            require: '输入不可为空'
        }
    }
}
