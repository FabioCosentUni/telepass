package exception.user;

public class VehicleException extends Exception{

        private final VehicleError cause;

        public VehicleException(VehicleError cause) {
            super(cause.getErrorMessage());
            this.cause = cause;
        }

        public VehicleError getErrorCause() {
            return cause;
        }
}
