;; to install add this to your init file:
;; (add-hook 'clojure-mode-hook
;;              (lambda ()
;;                (define-key clojure-mode-map "\C-xC-s"
;;                            'migae-save-buffer)))

(defvar repl-url "http://localhost:8080/repl")

(defun migae-repl ()
  "access repl url to force reload"
  ;; see http://www.emacswiki.org/emacs/DownloadFilesViaHttp
  (let* ((proc (open-network-stream "migae"
				    nil ;; "*migae*" ;; buffer
                                    "localhost"
                                    8080)))
    (process-send-string proc "GET /repl HTTP/1.0\r\n\r\n")
    ;; Watch us spin and stop Emacs from doing anything else!
    (message "accessing localhost:8080/repl to force reload...")
    (while (equal (process-status proc) 'open)
      (when (not (accept-process-output proc 180))
        (delete-process proc)
        (error "Network timeout!")))))

(defun migae-eval (&optional args)
  (interactive "p")
  ;; load repl url, then delete *.clj files from war/WEB-INF/classes
  (if (assoc 'srcroot file-local-variables-alist)
      (let* ((app (cdr (assoc 'app file-local-variables-alist)))
	     (srcroot (file-name-as-directory
		       (substitute-in-file-name
			(cdr (assoc 'srcroot file-local-variables-alist)))))
	     (warroot (file-name-as-directory
		       (substitute-in-file-name
			(cdr (assoc 'warroot file-local-variables-alist))))))
	(migae-repl)
	(message "repl accessed")
	(let* ((d (concat warroot "/WEB-INF/classes/" app))
	       (files (directory-files d ;; dir
				       t  ;; absolute file names
				       ".*.clj$")))
	  (dolist (f files)
	    (message (concat "deleting " f))
	    (delete-file f))))))


(defun migae-save-clj-buffer (&optional args)
  (interactive "p")
  (save-buffer)
  (if (assoc 'srcroot file-local-variables-alist)
      (let* ((srcroot (file-name-as-directory (substitute-in-file-name (cdr (assoc 'srcroot file-local-variables-alist)))))
	     (warroot (file-name-as-directory (substitute-in-file-name (cdr (assoc 'warroot file-local-variables-alist)))))
	     (srcfile (buffer-file-name))
	     (relname (file-relative-name srcfile srcroot))
	     (tgtfile (concat warroot "/classes/" relname)))
	;; (message "srcroot %s\nreltest %s" srcroot (file-relative-name srcfile srcroot))
	(message "copying %s to %s..." srcfile tgtfile)
	(make-directory (file-name-directory tgtfile) t)
	(copy-file srcfile tgtfile t))))
;; (and modp (memq args '(4 64)) (setq buffer-backed-up nil))))

(defun migae-save-xml-buffer (&optional args)
  (interactive "p")
  (save-buffer)
  (if (assoc 'srcroot file-local-variables-alist)
      (let* ((srcroot (file-name-as-directory (substitute-in-file-name (cdr (assoc 'srcroot file-local-variables-alist)))))
	     (warroot (file-name-as-directory (substitute-in-file-name (cdr (assoc 'warroot file-local-variables-alist)))))
	     (srcfile (buffer-file-name))
	     (relname (file-relative-name srcfile srcroot))
	     (tgtfile (concat warroot relname)))
	;; (message "srcroot %s\nreltest %s" srcroot (file-relative-name srcfile srcroot))
	(message "copying %s to %s..." srcfile tgtfile)
	(make-directory (file-name-directory tgtfile) t)
	(copy-file srcfile tgtfile t))))
;; (and modp (memq args '(4 64)) (setq buffer-backed-up nil))))

(provide 'migae)
