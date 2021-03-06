/**
 * This is a mixin that should be used by any ExtJS component wanting to dynamically coordinate and
 * change patient context without a browser refresh.
 *
 * This mixin primarily provides two events (beforepatientchange and patientchange) and current patient
 * field (pid).
 *
 * The <pre>beforepatientchange</pre> event gives each component an opportunity to veto the context change
 * (for example if there are dirty editors) or to use alert() and confirm().
 *
 * The <pre>patientchange</pre> event must be implemented by each component to explicitly
 * discard the prior patient context (if any) and acknowledge the new PID as current context
 * by setting the <pre>pid</pre> field and returning true.
 *
 * The <pre>setPatientContext()</pre> method can be called on any PatientAware component
 * (or statically via {@link org.osehra.hmp.PatientContext#setPatientContext} to initate the multi-step
 * process of switching patient context.
 *
 * See {@link org.osehra.hmp.PatientContext} for more details.
 */
Ext.define('org.osehra.hmp.PatientAware', {
    requires:[
        'org.osehra.hmp.PatientContext'
    ],
    pid:0, // the current patient ID according to this component (0=no patient currently selected)
    patientAware:true, // this is the way to identify/query for all patient aware mixin objects

    constructor:function (config) {
        this.addEvents(
            /**
             * @event beforepatientchange
             *
             * Gives each component an opportunity to veto the context change
             * (for example if there are dirty editors) or to use alert() and confirm().
             */
            'beforepatientchange',
            /**
             * @event patientchange
             *
             * Must be implemented by each component to explicitly
             * discard the prior patient context (if any) and acknowledge the new PID as current context
             * by setting the <pre>pid</pre> field and returning true.
             */
            'patientchange',
            /**
             * @event patientupdate
             */
            'patientupdate');
        return this.callParent(config);
    },

    /**
     * returns the current patient info
     */
    getPatientInfo:function () {
        return org.osehra.hmp.PatientContext.getPatientInfo();
    },

    setPatientContext:function (pid) {
        // simply delegate from this instance method to the singleton
        org.osehra.hmp.PatientContext.setPatientContext(pid);
    },

    setPatientUpdate:function (domains) {
        org.osehra.hmp.PatientContext.setPatientUpdate(domains);
    },

    initPatientContext:function () {
        var pid = org.osehra.hmp.PatientContext.pid;
        if (pid > 0) {
            if (this.fireEvent('beforepatientchange', pid) !== true) {
                org.osehra.hmp.PatientContext.reportError('Unable to initialize patient context!', this);
                return;
            }
            if (this.fireEvent('patientchange', pid) !== true) {
                org.osehra.hmp.PatientContext.reportError('Unable to initialize patient context!', this);
            }
        }
    }
});
