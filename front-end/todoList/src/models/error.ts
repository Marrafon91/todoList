export type ValidationError = {
  fieldName: string;
  message: string;
};

export type ValidationErrorResponse = {
  errors: ValidationError[];
};
