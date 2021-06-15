import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';

export function validandoCep(): ValidatorFn {
    return (control:AbstractControl) : ValidationErrors | null => {

        const value = control.value;

        if (!value) {
            return null;
        }

        return {valido:true};
    }
}
