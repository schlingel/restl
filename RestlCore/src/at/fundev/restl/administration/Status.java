package at.fundev.restl.administration;

/**
 * The Status enumeration. Representing the Status.
 */
public enum Status {
	/**
	 * The Request is known by the Status DB table, but the intent isn't send to the service yet.
	 */
	Created,
	/**
	 * The Request is as intent received by the service and enqueued.
	 */
	Pending,
	/**
	 * The request intent is dequeued and currently processed by the service.
	 */
	Processing,
	/**
	 * The network request succeeded. The result is persisted if possible.
	 */
	Finished,
	/**
	 * The network request failed. The result is persisted if possible.
	 */
	Error;
	
	public static int CREATED = 0;
	
	public static int PENDING = 1;
	
	public static int PROCESSING = 2;
	
	public static int FINISHED = 3;
	
	public static int ERROR = 4;
	
	/**
	 * Converts the given integer representation to a Status object. Throws an IllegalArgumentException if the value isn't valid.
	 * @throws IllegalArgumentException
	 * @param dRepresentation
	 * @return
	 */
	public static Status toStatus(int dRepresentation) {
		Status s = null;
		
		switch(dRepresentation) {
			case 0:
				s = Status.Created;
				break;
			case 1:
				s = Status.Pending;
				break;
			case 2:
				s = Status.Processing;
				break;
			case 3:
				s = Status.Finished;
				break;
			case 4:
				s = Status.Error;
				break;
			default:
				throw new IllegalArgumentException("Not valid integer representation!");
		}
		
		return s;
	}
	
	/**
	 * Returns the integer representation of the status object. Throws InvalidArgumentException if the status object doesn't contain a valid status.
	 * @throws IllegalArgumentException
	 * @param status
	 * @return
	 */
	public static int toInteger(Status status) {
		int val = -1;
		
		if(status == null) {
			throw new IllegalArgumentException("status must not be null!");
		} else {
			switch(status) {
				case Created:
					val = Status.CREATED;
					break;
				case Pending:
					val = Status.PENDING;
					break;
				case Processing:
					val = Status.PROCESSING;
					break;
				case Finished:
					val = Status.FINISHED;
					break;
				case Error:
					val = Status.ERROR;
					break;
				default:
					throw new IllegalArgumentException();
			}
			
			return val;
		}
	}
}
