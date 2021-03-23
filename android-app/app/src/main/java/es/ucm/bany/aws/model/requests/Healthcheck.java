package es.ucm.bany.aws.model.requests;

class Healthcheck extends Request<String, Void, String> {

    // Constructors
    // ------------------------------------------------------
    protected Healthcheck(String url) {
        super(url);
    }

    // Overrides
    // ------------------------------------------------------
    protected void onSuccess(String response) {
        Cloud.getInstance().notifyHealthCheckOk();
    }

    protected void onError(String error) {
        Cloud.getInstance().notifyHealthCheckError(error);
    }
}
