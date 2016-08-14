package il.org.spartan.refactoring.wring;

import static il.org.spartan.refactoring.utils.Funcs.*;

import org.eclipse.jdt.core.dom.*;

import il.org.spartan.refactoring.preferences.*;
import il.org.spartan.refactoring.utils.*;

/**
 * /** A {@link Wring} to convert <code>if (x) return b; else { }</code> into
 * <code>if (x) return b;</code>
 *
 * @author Yossi Gil
 * @since 2015-08-01
 */
public final class IfDegenerateElse extends Wring.ReplaceCurrentNode<IfStatement> implements Kind.Simplify {
  @Override Statement replacement(final IfStatement s) {
    final IfStatement $ = duplicate(s);
    $.setElseStatement(null);
    return !Is.blockRequiredInReplacement(s, $) ? $ : Subject.statement($).toBlock();
  }
  @Override boolean scopeIncludes(final IfStatement s) {
    return s != null && then(s) != null && degenerateElse(s);
  }
  @Override String description(final IfStatement s) {
    return "Remove vacuous 'else' branch of if(" + s.getExpression() + ") ...";
  }
  static boolean degenerateElse(final IfStatement s) {
    return elze(s) != null && Is.vacuousElse(s);
  }
}