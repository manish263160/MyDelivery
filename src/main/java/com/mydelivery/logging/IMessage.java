package com.mydelivery.logging;

public interface IMessage {
	/**
	 * Use the FATAL level priority for events that indicate a critical service
	 * failure. If a service issues a FATAL error it is completely unable to
	 * service requests of any kind.
	 */
	public static final int FATAL = 1;

	/**
	 * Use the ERROR level priority for events that indicate a disruption in a
	 * request or the ability to service a request. A service should have some
	 * capacity to continue to service requests in the presence of ERRORs.
	 */
	public static final int ERROR = 2;

	/**
	 * Use the WARN level priority for events that may indicate a non-critical
	 * service error. Resumable errors, or minor breaches in request
	 * expectations fall into this category. The distinction between WARN and
	 * ERROR may be hard to discern and so its up to the developer to judge. The
	 * simplest criterion is would this failure result in a user support call.
	 * If it would use ERROR. If it would not use WARN.
	 */
	public static final int WARN = 3;

	/**
	 * Use the INFO level priority for service life-cycle events and other
	 * crucial related information. Looking at the INFO messages for a given
	 * service category should tell you exactly what state the service is in.
	 */
	public static final int INFO = 4;

	/**
	 * Use the DEBUG level priority for log messages that convey extra
	 * information regarding life-cycle events. Developer or in depth
	 * information required for support is the basis for this priority. The
	 * important point is that when the DEBUG level priority is enabled, the
	 * JBoss server log should not grow proportionally with the number of server
	 * requests. Looking at the DEBUG and INFO messages for a given service
	 * category should tell you exactly what state the service is in, as well as
	 * what server resources it is using: ports, interfaces, log files, etc.
	 */
	public static final int DEBUG = 5;

	/**
	 * Use TRACE level priority for log messages that are directly
	 * associated with activity that corresponds requests. Further, such
	 * messages should not be submitted to a Logger unless the Logger category
	 * priority threshold indicates that the message will be rendered. Use the
	 * Logger.isTraceEnabled() method to determine if the category priority
	 * threshold is enabled. The point of the TRACE priority is to allow for
	 * deep probing of the JBoss server behavior when necessary. When the TRACE
	 * level priority is enabled, you can expect the number of messages in the
	 * JBoss server log to grow at least a x N, where N is the number of
	 * requests received by the server, a some constant. The server log may well
	 * grow as power of N depending on the request-handling layer being traced.
	 */
	public static final int TRACE = 6;
}
