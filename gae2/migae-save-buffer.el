(defun migae-save-buffer (&optional args)
  "Save current buffer in visited file if modified.
Also copy saved file to war/WEB-INF/classes.
Variations are described below.

By default, makes the previous version into a backup file
 if previously requested or if this is the first save.
Prefixed with one \\[universal-argument], marks this version
 to become a backup when the next save is done.
Prefixed with two \\[universal-argument]'s,
 unconditionally makes the previous version into a backup file.
Prefixed with three \\[universal-argument]'s, marks this version
 to become a backup when the next save is done,
 and unconditionally makes the previous version into a backup file.

With a numeric argument of 0, never make the previous version
into a backup file.

If a file's name is FOO, the names of its numbered backup versions are
 FOO.~i~ for various integers i.  A non-numbered backup file is called FOO~.
Numeric backups (rather than FOO~) will be made if value of
 `version-control' is not the atom `never' and either there are already
 numeric versions of the file being backed up, or `version-control' is
 non-nil.
We don't want excessive versions piling up, so there are variables
 `kept-old-versions', which tells Emacs how many oldest versions to keep,
 and `kept-new-versions', which tells how many newest versions to keep.
 Defaults are 2 old versions and 2 new.
`dired-kept-versions' controls dired's clean-directory (.) command.
If `delete-old-versions' is nil, system will query user
 before trimming versions.  Otherwise it does it silently.

If `vc-make-backup-files' is nil, which is the default,
 no backup files are made for files managed by version control.
 (This is because the version control system itself records previous versions.)

See the subroutine `basic-save-buffer' for more information."
  (interactive "p")
  (let ((modp (buffer-modified-p))
	(make-backup-files (or (and make-backup-files (not (eq args 0)))
			       (memq args '(16 64)))))
    (and modp (memq args '(16 64)) (setq buffer-backed-up nil))
    ;; We used to display the message below only for files > 50KB, but
    ;; then Rmail-mbox never displays it due to buffer swapping.  If
    ;; the test is ever re-introduced, be sure to handle saving of
    ;; Rmail files.
    (if (and modp (buffer-file-name))
	(message "Saving file %s..." (buffer-file-name)))
    (basic-save-buffer)

    (let* ((srcroot (file-name-as-directory (substitute-in-file-name (cdr (assoc 'srcroot file-local-variables-alist)))))
	   (warroot (file-name-as-directory (substitute-in-file-name (cdr (assoc 'warroot file-local-variables-alist)))))
	   (srcfile (buffer-file-name))
	   (relname (file-relative-name srcfile srcroot))
	   (tgtfile (concat warroot "WEB-INF/classes/" relname)))
      ;; (message "srcroot %s\nreltest %s" srcroot (file-relative-name srcfile srcroot))
      (message "copying %s to %s..." srcfile tgtfile)
      (make-directory (file-name-directory tgtfile) t)
      (copy-file srcfile tgtfile t))

    (and modp (memq args '(4 64)) (setq buffer-backed-up nil))))
